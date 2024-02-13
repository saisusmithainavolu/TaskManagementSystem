// this file helps to make a call to REST API using axios library

import axios from "axios";
import { getToken } from "./AuthService";

// Calling get all todos REST API
const REST_API_BASE_URL = "http://localhost:8080/api/todos";


// Add a request interceptor
axios.interceptors.request.use(function (config) {
    
    config.headers['Authorization'] = getToken();

    return config;
  }, function (error) {
    // Do something with request error
    return Promise.reject(error);
  });

export const listTodos = () => {
    return axios.get(REST_API_BASE_URL);
}

// Calling create todo REST API
export const addTodo = (todo) => axios.post(REST_API_BASE_URL,todo);

// Calling get todo by id REST API
export const getTodo = (id) => axios.get(REST_API_BASE_URL + '/' + id);

// Calling update todo by id REST API
export const updateTodo= (id,todo) => axios.put(REST_API_BASE_URL + '/' + id, todo);

// Calling delete todo by id REST API
export const deleteTodo = (id) => axios.delete(REST_API_BASE_URL + '/' + id);

// Calling complete todo by id REST API
export const completeTodo = (id) => axios.patch(REST_API_BASE_URL + '/' + id + '/complete');

// Calling incomplete todo by id REST API
export const inCompleteTodo = (id) => axios.patch(REST_API_BASE_URL + '/' + id + '/incomplete');