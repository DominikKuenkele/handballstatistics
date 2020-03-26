<template>
  <div class="home">
    <section class="left-home">
      <hs-home-item></hs-home-item>
    </section>
    <section class="right-home">
      <section class="upper-right">
        <hs-home-item></hs-home-item>
      </section>
      <section class="lower-right">
        <hs-home-item></hs-home-item>
      </section>
    </section>
  </div>
</template>

<script>
import HomeItem from '../components/HomeItem'


export default {
  components: {
    'hs-home-item': HomeItem,
  }

}
</script>

<style scoped>
.home{
  display:flex;
  height:100%;
  width:100%;
  position: relative;
}

.left-home{
  width:60%;
  height:100%;
  background-color: #C5E1A5;
}

.right-home{
  display: flex;
  flex-direction: column;
  width:40%;
  height:100%;
}

.upper-right{
  height:50%;
  background-color: #EF9A9A
  /*background-color:red;*/
}

.lower-right{
  height:50%;
  background-color:#90CAF9;
}

</style>
