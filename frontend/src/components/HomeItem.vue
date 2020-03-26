<template>
  <v-hover id="item-container" v-slot:default="{ hover }">
    <v-card :elevation="hover ? 16 : 2" tile outlined class="item-card">
      <div class="image-container">
        <img src="../assets/logo.svg" alt class="image" />
      </div>

      <div class="text-container">
        <h2 class="text-center">Play</h2>
        <span class="text-center">a match</span>
      </div>
    </v-card>
  </v-hover>
</template>

<style scoped>
#item-container {
  height: 100%;
  position: relative;
  overflow: hidden;
}

.item-card:hover {
    z-index:1;
}

.image-container {
  width: 100%;
  position: absolute;
  top: 0;
  margin: 10px 10px 10px 10px;
}

.text-container {
  position: absolute;
  width: 100%;
  bottom: 0;
}

.image {
  display: block;
  width: 40%;
  margin: auto;
}

h2 {
  font-size: 3em;
}
</style>
