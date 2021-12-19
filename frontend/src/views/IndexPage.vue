<template>
  <div>
    <NavigationBar/>
    <MainSearch/>
  </div>
</template>

<script>
import NavigationBar from "@/components/nav/NavigationBar";
import MainSearch from "@/components/MainSearch";
import {mapActions, mapGetters} from "vuex";

export default {
  name: "Index",
  components: {
    MainSearch,
    NavigationBar
  },
  computed: {
    ...
        mapGetters([
          'IS_AUTH'
        ])
  },
  methods: {
    ...mapActions([
      'CHECK_AUTH'
    ])
  },
  mounted() {
  }
}
</script>

<style scoped>

</style>