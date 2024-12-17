import axios from 'axios';
import {Ad} from '../models/Ad';
import {AdCategory} from '../models/AdCategory';
import {AdCopy} from '../models/AdCopy';
import {TrendingAdCategory} from '../models/TrendingAdCategory';

// Ad service
// Ad
const adServiceApi = axios.create({
    baseURL: 'http://localhost:8189/api/v1/ads', // Default URL for Ad Service
    timeout: 5000,
    headers: {
        'Content-Type': 'application/json',
    },
});

export const getAllAds = async (): Promise<Ad[]> => {
    try {
        const response = await adServiceApi.get<Ad[]>('/');
        return response.data;
    } catch (error) {
        console.error('Error fetching Ad Service data', error);
        throw error;
    }
};

export const getAdById = async (id: number): Promise<Ad> => {
    try {
        const response = await adServiceApi.get<Ad>(`/${id}`);
        return response.data;
    } catch (error) {
        console.error(`Error fetching ad with ID ${id}`, error);
        throw error;
    }
};

export const createAd = async (ad: Ad): Promise<Ad> => {
    try {
        const response = await adServiceApi.post<Ad>('/', ad);
        return response.data;
    } catch (error) {
        console.error('Error creating ad', error);
        throw error;
    }
};

export const deleteAd = async (id: number): Promise<void> => {
    try {
        await adServiceApi.delete(`/${id}`);
        console.log(`Ad with ID ${id} deleted`);
    } catch (error) {
        console.error(`Error deleting ad with ID ${id}`, error);
        throw error;
    }
};

// Ad service
// Category
const adCategoryServiceApi = axios.create({
    baseURL: 'http://localhost:8189/api/v1/categories', // Ad Category Service base URL
    timeout: 5000,
    headers: {
        'Content-Type': 'application/json',
    },
});

export const getAllCategories = async (): Promise<AdCategory[]> => {
    try {
        const response = await adCategoryServiceApi.get<AdCategory[]>('/');
        return response.data;
    } catch (error) {
        console.error('Error fetching all categories', error);
        throw error;
    }
};

export const getCategoryById = async (id: number): Promise<AdCategory> => {
    try {
        const response = await adCategoryServiceApi.get<AdCategory>(`/${id}`);
        return response.data;
    } catch (error) {
        console.error(`Error fetching category with ID ${id}`, error);
        throw error;
    }
};

// Listing Service
// Ad Copy
const adCopyServiceApi = axios.create({
    baseURL: 'http://localhost:8190/api/v1/ads', // Base URL
    timeout: 5000,
    headers: {
        'Content-Type': 'application/json',
    },
});

export const getListOfAds = async (): Promise<AdCopy[]> => {
    try {
        const response = await adCopyServiceApi.get<AdCopy[]>('/');
        return response.data;
    } catch (error) {
        console.error('Error fetching Ad copy data from Listing Service', error);
        throw error;
    }
};

// Ad Copy
const trendingAdCategoryServiceApi = axios.create({
    baseURL: 'http://localhost:8190/api/v1/trending-categories', // Base URL
    timeout: 5000,
    headers: {
        'Content-Type': 'application/json',
    },
});

// Get trending ad categories
export const getTop3TrendingAdCategories = async (): Promise<TrendingAdCategory[]> => {
    try {
        const response = await trendingAdCategoryServiceApi.get<TrendingAdCategory[]>('/');
        return response.data;
    } catch (error) {
        console.error('Error fetching Trending Ad Categories', error);
        throw error;
    }
};