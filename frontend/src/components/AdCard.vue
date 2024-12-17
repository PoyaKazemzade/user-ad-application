<script setup lang="ts">
defineProps({
  id: Number,
  created: {type: String, required: true},
  owner: {type: String, required: true},
  categoryName: {type: String, required: true},
  adTitle: {type: String, required: true},
  description: {type: String, required: true},
  price: {type: Number, required: true}
})

</script>

<template>
  <BCard
      :title="adTitle"
      img-src="https://placehold.co/600x300"
      :img-alt="adTitle"
      img-top
      tag="article"
      style="max-width: 20rem"
  >
    <BCardText>
      <div class="container">
        <span id="idTag">#{{ id }}</span>
        <span class="text-dark-emphasis d-block mb-1"><strong>User: </strong> {{ owner }}</span>
        <span class="text-dark-emphasis d-block mb-1"><strong>Published: </strong> {{ created }}</span>
        <span class="text-body d-block mb-1"> {{ description }}</span>
      </div>
    </BCardText>
    <BButton href="#" variant="primary">Contact seller</BButton>
  </BCard>
</template>

<style scoped>
#idTag {
  display: inline-block;
  font-size: 0.8rem;
  position: absolute;
  top: 0;
}
</style>