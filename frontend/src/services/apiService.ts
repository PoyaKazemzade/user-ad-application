import axios from 'axios';

// Ad service
// Ad
const adServiceApi = axios.create({
    baseURL: 'http://localhost:8189/api/v1/ads', // Default URL for Ad Service
    timeout: 5000,
    headers: {
        'Content-Type': 'application/json',
    },
});

export const getAllAds = async () => {
    try {
        const response = await adServiceApi.get('/');
        return response.data;
    } catch (error) {
        console.error('Error fetching Ad Service data', error);
        throw error;
    }
};

export const getAdById = async (id: number) => {
    try {
        const response = await adServiceApi.get(`/${id}`);
        return response.data;
    } catch (error) {
        console.error(`Error fetching ad with ID ${id}`, error);
        throw error;
    }
};

export const createAd = async (ad: any) => {
    try {
        const response = await adServiceApi.post('/', ad);
        return response.data;
    } catch (error) {
        console.error('Error creating ad', error);
        throw error;
    }
};

export const deleteAd = async (id: number) => {
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
    timeout: 5000, // Timeout after 5 seconds
    headers: {
        'Content-Type': 'application/json',
    },
});

export const getAllCategories = async () => {
    try {
        const response = await adCategoryServiceApi.get('/');
        return response.data; // Returns the list of ad categories
    } catch (error) {
        console.error('Error fetching all categories', error);
        throw error;
    }
};

export const getCategoryById = async (id: number) => {
    try {
        const response = await adCategoryServiceApi.get(`/${id}`);
        return response.data; // Returns the single category
    } catch (error) {
        console.error(`Error fetching category with ID ${id}`, error);
        throw error;
    }
};

// Listing Service
// Ad Copy
const adCopy = axios.create({
    baseURL: 'http://localhost:8080', // URL for Listing Service
    timeout: 5000, // Timeout after 5 seconds
    headers: {
        'Content-Type': 'application/json',
    },
});

// Function to make requests to Listing Service
export const getListingServiceData = async () => {
    try {
        const response = await adCopy.get('/listings'); // Adjust the endpoint as needed
        return response.data;
    } catch (error) {
        console.error('Error fetching Listing Service data', error);
        throw error;
    }
};
