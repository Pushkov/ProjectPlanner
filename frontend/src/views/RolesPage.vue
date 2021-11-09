<template>
  <div>
    <NavigationBar/>
    <RolesList/>
  </div>
</template>

<script>
import NavigationBar from "@/components/nav/NavigationBar";
import RolesList from "@/components/RolesList";
import {mapActions} from 'vuex'

export default {
  name: "Index",
  data() {
    return {}
  },
  components: {
    RolesList,
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