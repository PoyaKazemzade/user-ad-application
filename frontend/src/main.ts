import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import { BListGroup, BListGroupItem } from 'bootstrap-vue-next';

const app = createApp(App);
app.component('BListGroup', BListGroup); // Register BListGroup globally
app.component('BListGroupItem', BListGroupItem); // Register BListGroupItem globally
app.mount('#app');