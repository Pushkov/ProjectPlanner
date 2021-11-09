<template>
  <tr>
    <td>{{ role.id }}</td>
    <td>{{ role.name }}</td>
    <td>
      <div
          v-for="p of role.permissionDto"
          :role="p"
          :key="p.name"
      >
        <span>{{ p.name }}</span>
      </div>


    </td>
  </tr>
</template>

<script>
export default {
  name: "RoleTableRow",
  props: {
    role: {
      type: Object,
      default: () => {
        return {}
      }
    }
  },
  methods: {},
}
</script>

<style scoped>

</style>