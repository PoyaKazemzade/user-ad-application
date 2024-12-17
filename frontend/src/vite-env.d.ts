/// <reference types="vite/client" />
/// <reference types="vite/client" />

declare module '*.vue' {
    import {DefineComponent} from 'vue';
    const component: DefineComponent<{}, {}, any>;
    export default component;
}

interface ImportMeta {
    readonly env: ImportMetaEnv;
    readonly url?: string;
}

interface ImportMetaEnv {
    readonly BASE_URL: string;
}
