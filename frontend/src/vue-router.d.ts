import { ComponentCustomProperties } from 'vue';
import { RouteLocationNormalizedLoaded, Router } from 'vue-router';

declare module '@vue/runtime-core' {
    interface ComponentCustomProperties {
        $route: RouteLocationNormalizedLoaded;
        $router: Router;
    }
}
