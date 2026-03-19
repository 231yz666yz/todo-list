import request from "../utils/requests";

export default{

    getAllTasks(){
        return request.get('/tasks');
    },

    addTask(task){
        return request.post('/tasks', task);
    },

    updateTask(id, task){
        return request.put(`/tasks/${id}`, task);
    },

    deleteTask(id){
        return request.delete(`/tasks/${id}`);
    }
}