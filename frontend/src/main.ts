import { createApp } from 'vue'
import {createBootstrap} from 'bootstrap-vue-next'
import './style.css'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue-next/dist/bootstrap-vue-next.css'

import App from './App.vue'
import router from './router'

const app = createApp(App)
app.use(createBootstrap()) // Important
app.use(router)
app.mount('#app')