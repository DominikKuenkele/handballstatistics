<template>
  <v-card id="manage-tabs">
    <v-tabs v-model="tab" background-color="deep-purple accent-4" centered dark icons-and-text>
      <v-tabs-slider></v-tabs-slider>

      <v-tab href="#tab-1">
        Players
        <v-icon>mdi-pistol</v-icon>
      </v-tab>

      <v-tab href="#tab-2">
        Teams
        <v-icon>mdi-glass-mug-variant</v-icon>
      </v-tab>

      <v-tab href="#tab-3">
        Matches
        <v-icon>mdi-paper-roll</v-icon>
      </v-tab>
    </v-tabs>

    <v-tabs-items v-model="tab">
      <v-tab-item v-for="i in 3" :key="i" :value="'tab-' + i">
        <hs-search-field></hs-search-field>
        <hs-result-list></hs-result-list>
        <hs-manage-menu></hs-manage-menu>
      </v-tab-item>
    </v-tabs-items>
  </v-card>
</template>

<script>
import SearchField from "./SearchField";
import ResultList from "./ResultList";
import ManageMenu from "./ManageMenu";

export default {
  components: {
    "hs-search-field": SearchField,
    "hs-result-list": ResultList,
    "hs-manage-menu": ManageMenu
  },

  data() {
    return {
      tab: null,
      text: "gabel"
    };
  }
};
</script>

<style scoped>
hs-manage-menu {
    position: absolute;
    bottom: 0;
}

#manage-tabs {
    height: 100%;
}

</style>