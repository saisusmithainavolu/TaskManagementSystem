import React from 'react'
import { NavLink } from 'react-router-dom'
import { useNavigate } from 'react-router-dom'
import { isUserLoggedIn, logout } from '../Services/AuthService';

const HeaderComponent = () => {

  // check
  const isAuth = isUserLoggedIn();

    const navigator = useNavigate();

    function handleLogout(){
        logout();
        navigator('/login')
    }
  return (
    <div>
    <header>
        <nav className='navbar navbar-expand-md navbar-dark bg-dark'>
            <div>
                <a href='http://localhost:3000' className='navbar-brand'>
                    Todo Management Application
                </a>
            </div>
            <div className='collapse navbar-collapse'>
                <ul className='navbar-nav'>

                    {
                      // display the todos link only after user logged in
                        isAuth &&                         
                        <li className='nav-item'>
                        <NavLink to="/todos" className="nav-link">Todos</NavLink>
                    </li>
                    }

                </ul>

            </div>
            <ul className='navbar-nav'>
                {
                  // display the register link only if the user is not logged in
                    !isAuth &&                         
                    <li className='nav-item'>
                    <NavLink to="/register" className="nav-link">Register</NavLink>
                </li>
                }

                {
                  // display the login link only if the user is not logged in
                    !isAuth &&    
                    <li className='nav-item'>
                    <NavLink to="/login" className="nav-link">Login</NavLink>
                </li>
                }

                {
                  // display the logout link only after user logged in
                    isAuth &&    
                    <li className='nav-item'>
                    <NavLink to="/login" className="nav-link" onClick={handleLogout}>Logout</NavLink>
                </li>
                }

                </ul>
        </nav>
    </header>

</div>
  )
}

export default HeaderComponent