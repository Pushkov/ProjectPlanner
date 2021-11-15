<template>
  <tr @click="engineerInModal">
    <td>{{ engineer.id }}</td>
    <td>{{ engineer.firstName }}</td>
    <td>{{ engineer.lastName }}</td>
  </tr>
</template>

<script>
export default {
  name: "EngineerTableRow",
  props: {
    engineer: {
      type: Object,
      default: () => {
        return {}
      }
    }
  },
  methods: {
    engineerInModal() {
      this.$emit('showEngineerModal', this.engineer);
    }
  },
}
</script>

<style scoped>

</style>