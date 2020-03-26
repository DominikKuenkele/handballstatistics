<template>
  <div class="manage">
    <section class="left-manage">
      <hs-manage-tabs></hs-manage-tabs>
    </section>
    <section class="right-manage"></section>
  </div>
</template>

<script>
import ManageTabs from "../components/ManageTabs";

export default {
  components: {
    "hs-manage-tabs": ManageTabs
  }
};
</script>

<style scoped>
.manage {
  display: flex;
  height: 100%;
  width: 100%;
  position: relative;
}

.left-manage {
  width: 34%;
  height: 100%;
  background-color: rgb(215, 165, 225);
}

.right-manage {
  width: 66%;
  height: 100%;
  background-color: rgb(156, 158, 154);
}
</style>