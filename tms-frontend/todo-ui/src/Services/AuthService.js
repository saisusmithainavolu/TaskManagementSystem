// this file helps to make a call to REST API using axios library

import axios from "axios";

// Calling get all todos REST API
const AUTH_REST_API_BASE_URL = "http://localhost:8080/api/auth";

// Calling register REST API
export const registerAPICall = (registerObj) => axios.post(AUTH_REST_API_BASE_URL + '/register', registerObj);

export const loginAPICall = (usernameOrEmail, password) => axios.post(AUTH_REST_API_BASE_URL + '/login', { usernameOrEmail, password});

export const storeToken = (token) => localStorage.setItem("token", token);

export const getToken = () => localStorage.getItem("token");

// save the logged in user in the session storage
export const saveLoggedInUser = (username,role) => { 
    sessionStorage.setItem("authenticatedUser", username);
    sessionStorage.setItem("role",role);
}

// validate whether the user is logged in or not
export const isUserLoggedIn = () => {

    const username = sessionStorage.getItem("authenticatedUser");

    if(username == null) {
        return false;
    }    
    else {
        return true;
    }   
}

// get the logged in user
export const getLoggedInUser = () => {
    const username = sessionStorage.getItem("authenticatedUser");
    return username;
}

// logout function to logout the user
export const logout = () => {
    // clear all the data from local and session storage
    localStorage.clear();
    sessionStorage.clear();
}

export const isAdminUser = () => {

    let role = sessionStorage.getItem("role");
    if(role != null && role === 'ROLE_ADMIN'){
        return true; 
    } else{
        return false;
    }
}
