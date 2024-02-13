import React, { useEffect, useState } from 'react'
import { useNavigate, useParams } from 'react-router-dom'
import { addTodo, getTodo, updateTodo } from '../Services/TodoService'

const TodoComponent = () => {

const [title,setTitle] = useState('')
const [description,setDescription] = useState('')
const [completed,setCompleted] = useState(false)
const[todos,settodos] = useState([])
const navigator = useNavigate();
const {id} = useParams();

useEffect(() => {
    if(id){
        getTodo(id).then((response) => {
            setTitle(response.data.title);
            setDescription(response.data.description);
            setCompleted(response.data.completed)
        }).catch(error => {
            console.error(error);
        })
    }
}, [id])

function saveOrUpdateTodo(event){
    event.preventDefault();
    const todo={title,description,completed}
    console.log(todo)

        if(id){
            updateTodo(id,todo).then((response) => { 
                console.log(response.data); 
                navigator('/todos')
        }).catch(error => {
            console.error(error);
        })
    } else{

        addTodo(todo).then((response) => { 
            console.log(response.data); 
            navigator('/todos')
        }).catch(error => {
            console.error(error);
        })
    }   
}   
    function pageTitle(){
        if(id){
            return <h2 className='text-center'> Update Todo</h2>
        } else{
            return <h2 className='text-center'> Add Todo</h2>
        }
    }
  return (
    <div className='container'>
        <br></br>
        <div className='row'>
            <div className='card col-md-6 offset-md-3 offset-md-3'>
                
            {
                    pageTitle()
            }
                <div className='card-body'>
                    <form>
                        <div className='form-group mb-2'>
                            <label className='form-label'> Title </label>
                            <input 
                            type='text' 
                            placeholder='Enter Todo title' 
                            name='title' 
                            value={title} 
                            className='form-control'
                            onChange={(event) => setTitle(event.target.value)}
                            >                                
                            </input>

                        </div> 
                        <div className='form-group mb-2'>
                            <label className='form-label'> Description </label>
                            <input 
                            type='text' 
                            placeholder='Enter Todo description' 
                            name='description' 
                            value={description} 
                            className='form-control'
                            onChange={(event) => setDescription(event.target.value)}
                            >                                
                            </input>
                           
                        </div> 
                        <div className='form-group mb-2'>
                            <label className='form-label'> Completion Status</label>
                            <select                        
                            value={completed} 
                            className='form-control'
                            onChange={(event) => setCompleted(event.target.value)}
                            >       
                            <option value='false'>No</option> 
                            <option value='true'>Yes</option>                           
                            </select>
                           
                        </div> 
                        <button className='btn btn-success' onClick={saveOrUpdateTodo} >Submit</button>
                    </form>
                </div>
            </div>
        </div>

    </div>
  )
}

export default TodoComponent