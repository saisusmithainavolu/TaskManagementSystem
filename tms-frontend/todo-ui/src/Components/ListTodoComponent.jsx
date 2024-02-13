import React, { useEffect, useState } from 'react'
import { completeTodo, deleteTodo, inCompleteTodo, listTodos } from '../Services/TodoService';
import { useNavigate } from 'react-router-dom';
import { isAdminUser } from '../Services/AuthService';

const ListTodoComponent = () => {

const navigator = useNavigate();
const [todos,setTodos] = useState([])

const isAdmin = isAdminUser();

useEffect(() => {
    getAllTodos();
 },[])

 function getAllTodos(){
     listTodos().then((response => {
         setTodos(response.data);
         })).catch(error => {
             console.error(error);
         })
 }

 function addTodo(){
    navigator("/add-todo")
 }

 function updateTodo(id){
    navigator(`/update-todo/${id}`)
}

function removeTodo(id){
    console.log(id);

    deleteTodo(id).then((response) =>{
     getAllTodos();
     }).catch(error => {
         console.error(error);
     })
 }

 function markCompleteTodo(id){
    console.log(id);
    completeTodo(id).then((response) =>{
        getAllTodos();
        }).catch(error => {
            console.error(error);
        })

 }

 function markInCompleteTodo(id){
    console.log(id);
    inCompleteTodo(id).then((response) =>{
        getAllTodos();
        }).catch(error => {
            console.error(error);
        })

 }


  return (
    <div className = 'container'>
        <br></br>
    <h2 className='text-center'>List of Todos</h2>
    {
        isAdmin && 
        <button className='btn btn-primary mb-2' onClick={addTodo}> Add Todo </button>
    }
    <div>
    <table className='table table-striped table-bordered'>
        <thead>
            <tr>
                <th>Todo Title</th>
                <th>Todo Description</th>
                <th>Todo Completion Status</th>
                <th>Actions</th>
                </tr>
        </thead>
        <tbody>
            {
                todos.map(todo =>
                    <tr key={todo.id}>
                        <td>{todo.title}</td>
                        <td>{todo.description}</td>
                        <td>{todo.completed ? 'YES' : 'NO'}</td>
                        <td>
                            {
                                isAdmin &&
                                <button className='btn btn-info' onClick={()=>updateTodo(todo.id)} 
                                    style={{marginRight: '10px'}}>Update</button>
                            }
                            {
                                isAdmin && 
                                <button className='btn btn-danger' onClick={()=>removeTodo(todo.id)}
                                    style={{marginRight: '10px'}}>Delete</button>
                            }                             
                                    
                                    <button className='btn btn-success' onClick={()=>markCompleteTodo(todo.id)}
                                    style={{marginRight: '10px'}}>Complete</button>
                                    <button className='btn btn-info' onClick={()=>markInCompleteTodo(todo.id)}>In Complete</button>
                        </td>
                        </tr>)
            }
        </tbody>
    </table>
    </div>
</div>
  )
}

export default ListTodoComponent