<template>
  <div id="item-container">
      <div class="image-container">
        <img src="../assets/logo.svg" alt="" class="image">
      </div>
      
      <div class="text-container">
          <h2>Play</h2>
          <span> a match</span>      
    </div>
  </div>
</template>

<style scoped>
#item-container {
    height: 100%;
    position: relative;
    overflow: hidden;
}

.image-container {
    width: 100%;
    position: absolute;
    top: 0;
    margin: 10px 10px 10px 10px;
}

.text-container {
    position: absolute;
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
