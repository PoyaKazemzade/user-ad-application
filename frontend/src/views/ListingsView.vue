<template>
  <h1>Listings</h1>
  <div v-if="$route.query.q" class="alert alert-info">
    Showing results for "{{ $route.query.q }}"
  </div>
  <div v-if="$route.query.q && allAds.length === 0" class="text-dark">
    <span>No items found</span>
  </div>
  <div class="mb-3">
    <b-button
        v-for="category in categories"
        :key="category.id"
        @click="getAdsByCategory(category.categoryName)"
        class="me-2">
      {{ category.categoryName }}
    </b-button>
  </div>
  <div class="container d-flex flex-wrap justify-content-center 
  align-items-center gap-3 mb-5">
    <AdCard
        v-for="ad in allAds"
        :key="ad.id"
        class="d-flex justify-content-center gap-2 align-items-start"
        :id="ad.id"
        :created="ad.created"
        owner="bob"
        :category-name="ad.categoryName"
        :ad-title="ad.title"
        :description="ad.description"
        :price="ad.price"/>
  </div>
</template>

<script setup lang="ts">
import {ref, onMounted, watch} from 'vue';
import {useRoute} from "vue-router";
import {getAdsForCategory, getAllCategories, getListOfAds, search} from '../services/apiService.ts';
import {AdCopy} from "../models/AdCopy.ts";
import AdCard from "@/components/AdCard.vue";
import {AdCategory} from "../models/AdCategory.ts";

const allAds = ref([] as AdCopy[]);
const categories = ref([] as AdCategory[]);

const route = useRoute();

onMounted(async () => {
  try {
    const query = route.query.q;
    if (query) {
      allAds.value = await search(query as string);
    } else {
      allAds.value = await getListOfAds();
      categories.value = await getAllCategories();
    }
  } catch (error) {
    console.error("Error fetching ads", error);
  }
});

const getAdsByCategory = async (categoryName: string) => {
  try {
    allAds.value = await getAdsForCategory(categoryName);
  } catch (error) {
    console.error(`Error fetching ads for category "${categoryName}":`, error);
  }
};

watch(() => route.query.q, async (newSearch) => {
  if (newSearch) {
    try {
      allAds.value = await search(newSearch as string);
    } catch (error) {
      console.error("Error searching ads:", error);
    }
  } else {
    allAds.value = await getListOfAds();
  }
});
</script>

<style scoped>
h1 {
  padding: 1rem;
}
</style>
