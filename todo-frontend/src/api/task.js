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

    toggleTask(id, complete){
        return request.post(`/tasks/toggle/${id}`, complete);
    },

    deleteTask(id){
        return request.delete(`/tasks/${id}`);
    }
}