<template>
  <div>
    <NavigationBarEngineers/>
    <EngineersList/>
  </div>
</template>

<script>
import NavigationBarEngineers from "@/components/NavigationBarEngineers";
import {mapActions} from 'vuex'
import EngineersList from "../components/EngineersList";

export default {
  name: "Index",
  data() {
    return {}
  },
  components: {
    EngineersList,
    NavigationBarEngineers,
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