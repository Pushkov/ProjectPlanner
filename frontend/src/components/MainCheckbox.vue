<template class="mt-xl-5">
  <b-check :checked="isEdit" @change="isChecked">{{ title }}</b-check>
</template>

<script>
  import {mapActions, mapGetters} from 'vuex'

  export default {
    name: "MainCheckbox",
    props: {
      title: String,
      isEdit: {
        type: Boolean,
        default: false
      }
    },
    data() {
      return {}
  },
  components: {},
  computed: {
    ...mapGetters([])
  },
  methods: {
    ...mapActions([]),
    isChecked() {
      this.isEdit = !this.isEdit;
      this.$emit('isChecked', this.isEdit);
    }
  },
  mounted() {
  }
}
</script>

<style scoped>

</style>