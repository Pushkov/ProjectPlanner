<template>
  <div>
    <NavigationBarEngineers/>
    <RolesList/>
  </div>
</template>

<script>
import NavigationBarEngineers from "@/components/NavigationBarEngineers";
import {mapActions} from 'vuex'
import RolesList from "../components/RolesList";

export default {
  name: "Index",
  data() {
    return {}
  },
  components: {
    RolesList,
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