<template>
  <h1>Listings</h1>
  <b-button
      @click="getAdsByCategory('vehicles')">
    Vehicles
  </b-button>
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
import {getAdsForCategory, getListOfAds} from '../services/apiService.ts';
import {AdCopy} from "../models/AdCopy.ts";
import AdCard from "@/components/AdCard.vue";

const allAds = ref([] as AdCopy[]);

onMounted(async () => {
  try {
    allAds.value = await getListOfAds();
  } catch (error) {
    console.error("Error fetching ads:", error);
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
