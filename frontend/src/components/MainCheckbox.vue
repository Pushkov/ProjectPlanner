<template class="mt-xl-5">
  <b-check>{{ title }}</b-check>
</template>

<script>
import {mapActions, mapGetters} from 'vuex'

export default {
  name: "MainCheckbox",
  props: {
    title: String,
  },
  data() {
    return {}
  },
  components: {},
  computed: {
    ...mapGetters([])
  },
  methods: {
    ...mapActions([])
  },
  mounted() {
  }
}
</script>

<style scoped>

</style>