import { createRouter, createWebHistory } from 'vue-router';
import MainLayout from '@/components/layout/MainLayout.vue';

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/',
            redirect: '/home',  // Redirect root to /home
        },
        {
            path: '/home',
            component: MainLayout,  // MainLayout for /home
            children: [
                {
                    path: '',
                    name: 'start',
                    component: () => import('../views/StartView.vue'),
                },
                {
                    path: 'listings',
                    name: 'ads',
                    component: () => import('../views/ListingsView.vue'),
                },
            ],
        },
    ],
});

export default router;