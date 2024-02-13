import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import ListTodoComponent from './Components/ListTodoComponent'
import HeaderComponent from './Components/HeaderComponent'
import FooterComponent from './Components/FooterComponent'
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import TodoComponent from './Components/TodoComponent'
import RegisterComponent from './Components/RegisterComponent'
import LoginComponent from './Components/LoginComponent'
import { isUserLoggedIn } from './Services/AuthService'

function App() {

  // function to make sure user access the add-todo,update-todo functionalities only if he is logged in
  function AuthenticatedRoute({children}){

    const isAuth = isUserLoggedIn();

    // if user is not authenticated then we will navigate user to login component corresponding link '/'
    if(isAuth) {
      return children;
    }

    return <Navigate to="/" />

  }

  return (
    <>
    <BrowserRouter>
    <HeaderComponent/>      
      <Routes>
         {/* // http://localhost:3000 */}
         <Route path='/' element = { <LoginComponent/>}></Route>
         {/* // http://localhost:3000/todos */}
         <Route path='/todos' element = { 
          <AuthenticatedRoute>
            {/* listtodocomponent is a children to AuthenticatedRoute component */}
            <ListTodoComponent/>
          </AuthenticatedRoute>
         }></Route>
         {/* // http://localhost:3000/add-todo */}
         <Route path='/add-todo' element = { 
          <AuthenticatedRoute>
            {/* todocomponent is a children to AuthenticatedRoute component */}
            <TodoComponent/>
         </AuthenticatedRoute>
         }></Route>
         {/* // http://localhost:3000/update-todo/1 */}
         <Route path='/update-todo/:id' element = { 
          <AuthenticatedRoute>
            <TodoComponent />
         </AuthenticatedRoute>
         }></Route>
         {/* // http://localhost:3000/register */}
         <Route path='/register' element = { <RegisterComponent />}></Route>
         {/* // http://localhost:3000/login */}
         <Route path='/login' element = { <LoginComponent />}></Route>
      </Routes>
      <FooterComponent/>
      </BrowserRouter>
    </>
  )
}

export default App
