<template>
  <div>
    <h1 class="text-center">Welcome to Bazaar</h1>
    <p class="bg-primary text-bg-primary">Your favorite online marketplace</p>
  </div>
  <div class="parent">
    <div class="div1">
      <img class="figure fig" :src="TestImage" alt="Hello">
    </div>
    <div class="div2 align-content-center bg-body-tertiary">
      <span class="blockquote text-center">
        "I just wanna buy your stuff." - anonymous
      </span>
    </div>
    <div class="div3 bg-primary text-bg-primary">
      <p>
        Lorem ipsum dolor sit amet, consectetur adipiscing elit.
        Nullam sed metus id metus elementum facilisis.
        In orci mi, interdum sit amet arcu ut, egestas luctus sapien.
        Praesent viverra tortor enim, et rutrum augue aliquam nec.
        Curabitur porta tempus vehicula. Aenean id lobortis turpis.
      </p>
      <p>
        Phasellus ullamcorper turpis ut odio congue, nec hendrerit dui mollis.
        Curabitur ornare ex vel orci egestas, eu aliquet ante aliquet.
        Sed sit amet dolor ac velit malesuada pellentesque.
      </p>
    </div>
    <div class="div4">
      <h2 class="mt-1 ">Top categories</h2>
      <BListGroup>
        <BListGroupItem
            v-for="(category, index) in topCategories"
            :key="category.id"
            class="d-flex justify-content-center gap-2 align-items-start"
        >
          <span class="badge bg-success">{{ index + 1}}</span>
          <span> {{ category.categoryName }} - ({{ category.adCount }} ads)</span>
        </BListGroupItem>
      </BListGroup>
    </div>
  </div>
</template>

<script setup lang="ts">
import {ref, onMounted} from 'vue';
import {getTop3TrendingAdCategories} from '../services/apiService.ts';
import {TrendingAdCategory} from '../models/TrendingAdCategory.ts';
import TestImage from '@/assets/images/20943818.jpg';

const topCategories = ref([] as TrendingAdCategory[]);

onMounted(async () => {
  topCategories.value = await getTop3TrendingAdCategories();
});
</script>

<style scoped>
h1 {
  padding-top: 1rem;
}

.fig {
  max-width: 400px;
}

.parent {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-template-rows: repeat(2, 1fr);
  grid-column-gap: 4px;
  grid-row-gap: 4px;
}

.div1 {
  grid-area: 1 / 1 / 2 / 2;
}

.div2 {
  grid-area: 1 / 2 / 2 / 3;
}

.div3 {
  grid-area: 2 / 1 / 3 / 2;
}

.div4 {
  grid-area: 2 / 2 / 3 / 3;
}

.vertical-txt {
  font-size: 4rem;
  transform: rotate(90deg);
  white-space: nowrap;
  padding: 1rem;
  display: flex;
  align-items: center;
  justify-content: center;
}
</style>

