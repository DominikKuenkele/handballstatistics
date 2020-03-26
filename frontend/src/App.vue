<template>
  <v-app>
    <hs-header />
    <router-view></router-view>
  </v-app>
</template>

<script>
import Header from './components/Header'

export default {
  name: 'App',

  components: {
    'hs-header': Header,
  },

  data: () => ({
    //
  }),
};
</script>
