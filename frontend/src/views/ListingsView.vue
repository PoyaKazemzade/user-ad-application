<template>
  <h1>Listings</h1>
  <div class="mb-3">
    <b-button
        v-for="category in categories"
        :key="category.id"
        @click="getAdsByCategory(category.categoryName)"
        class="me-2">
      {{ category.categoryName }}
    </b-button>
  </div>
  <div class="container d-flex flex-wrap justify-content-center align-items-center gap-3">
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
import {ref, onMounted} from 'vue';
import {getAdsForCategory, getAllCategories, getListOfAds} from '../services/apiService.ts';
import {AdCopy} from "../models/AdCopy.ts";
import AdCard from "@/components/AdCard.vue";
import {AdCategory} from "../models/AdCategory.ts";

const allAds = ref([] as AdCopy[]);
const categories = ref([] as AdCategory[]);

onMounted(async () => {
  try {
    allAds.value = await getListOfAds();

    categories.value = await getAllCategories();
  } catch (error) {
    console.error("Error fetching ads or all categories:", error);
  }
});

const getAdsByCategory = async (categoryName: string) => {
  try {
    allAds.value = await getAdsForCategory(categoryName);
  } catch (error) {
    console.error(`Error fetching ads for category "${categoryName}":`, error);
  }
};

</script>

<style scoped>
h1 {
  padding: 1rem;
}
</style>
