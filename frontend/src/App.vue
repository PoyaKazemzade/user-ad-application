<script setup lang="ts">
import {ref, onMounted} from "vue";
import {getAllCategories} from "./services/apiService.ts";
import {AdCategory} from "./models/AdCategory.ts";

const categories = ref<AdCategory[]>([])

const fetchCategories = async () => {
  try {
    categories.value = await getAllCategories()
  } catch (error) {
    console.error('Failed to fetch ad categories', error)
  }
}

onMounted(() => {
  fetchCategories()
})
</script>

<template>
  <div>
    <BListGroup>
      <BListGroupItem v-for="category in categories" :key="category.id">
        {{ category.categoryName }}
      </BListGroupItem>
    </BListGroup>
  </div>
</template>

