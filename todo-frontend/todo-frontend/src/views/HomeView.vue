<template>
    <div class="todo-container">
        <h1>Todo 清单</h1>
        <div class="add-box">
            <input v-model="newTask" placeholder="输入任务" @keyup.enter="add"/>
            <button @click="add">添加</button>
        </div>

        <ul class="task-container">
            <li v-for="item in taskList" :key="item.id">
                <span :class="{done: item.completed}" @click="toggleComplete(item)">
                    {{item.title}}
                </span>
                <button @click="del(item.id)">删除</button>
            </li>
        </ul>
    </div>
</template> 

<script setup>
    import { ref, onMounted } from 'vue'
    import taskApi from '../api/task'
    const { getAllTasks, addTask, updateTask, deleteTask } = taskApi

    const taskList = ref([])
    const newTask = ref("")

    onMounted(async () => {
    const res = await getAllTasks()
    taskList.value = res
    })

    const add = async () => {
        if(!newTask.value) return

        const task = {
            title: newTask.value,
            completed: false
        }

        await addTask(task)
        
        const res = await getAllTasks()
        taskList.value = res

        newTask.value = ""
    }
    
    const toggleComplete = async(item) => {
        await updateTask(item.id, {
            title: item.title,
            completed: item.completed
        })  
        
        const res = await getAllTasks()
        taskList.value = res
    }

    const del = async(id) => {
        await deleteTask(id)

        const res = await getAllTasks()
        taskList.value = res
    }
</script>

<style scoped>
.todo-container {
  max-width: 500px;
  margin: 50px auto;
  padding: 20px;
}
h1 {
  font-size: 36px;
  font-weight: 700;
  color: #2c3e50;
  text-align: center;
  margin-bottom: 30px;
  letter-spacing: 2px;
  background: linear-gradient(90deg, #42b983, #359e6f);
  -webkit-background-clip: text;
  background-clip: text;
  color: transparent;
  text-shadow: 0 2px 4px rgba(0,0,0,0.05);
}
.add-box {
  display: flex;
  gap: 8px;
  margin-bottom: 20px;
}
input {
  flex: 1;
  padding: 8px 12px;
  border: 1px solid #ddd;
  border-radius: 6px;
  outline: none;
}
input:focus {
  border-color: #42b983;
}

.add-box button {
  padding: 8px 16px;
  background: #42b983;
  color: #fff;
  border: none;
  border-radius: 6px;
  cursor: pointer;
}
.add-box button:hover {
  background: #359e6f;
}

.task-container {
  padding: 0;
}
li {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 14px;
  margin: 8px 0;
  background: #f9f9f9;
  border-radius: 8px;
  list-style: none;
}

li span {
  font-size: 15px;
  cursor: pointer;
}

.done {
  text-decoration: line-through;
  color: #999;
}

li button {
  padding: 4px 10px;
  font-size: 12px;
  color: #666;
  background: #f5f5f5;
  border: 1px solid #eee;
  border-radius: 6px;
  cursor: pointer;
}
li button:hover {
  background: #ffeaea;
  color: #f56c6c;
  border-color: #f56c6c;
}
</style>