<template class="mt-xl-5">
  <div class="w-75 m-auto py-3">
    <input class="row w-75 bg-active border rounded-lg m-auto">
    <div class="row w-75 bg-warn m-auto py-2">
      <div class="col-3"/>
      <MainCheckbox
          class="col-sm"
          :title="'Персонал'"
      />
      <MainCheckbox
          class="col-sm"
          :title="'Проекты'"
      />
      <MainCheckbox
          class="col-sm"
          :title="'Документы'"
      />
      <div class="col-3"/>
    </div>
  </div>
</template>

<script>
import {mapActions, mapGetters} from 'vuex'
import MainCheckbox from "@/components/MainCheckbox";

export default {
  name: "MainSearch",
  data() {
    return {}
  },
  components: {
    MainCheckbox
  },
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