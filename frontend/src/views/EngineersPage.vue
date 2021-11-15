<template>
  <div>
    <NavigationBar/>
    <EngineersList/>
  </div>
</template>

<script>
import NavigationBar from "@/components/nav/NavigationBar";
import EngineersList from "@/components/EngineersList";
import {mapActions} from 'vuex'

export default {
  name: "Index",
  data() {
    return {}
  },
  components: {
    EngineersList,
    NavigationBar,
  },
  methods: {
    ...mapActions([
      'GET'
    ])
  }
}
</script>

<style scoped>

</style>