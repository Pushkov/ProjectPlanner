<template>
  <div class="row m-0 p-0" v-if="item !== undefined">
    <small class="text-sm text-danger m-auto" style="font-size: x-small">{{ item }}</small>
  </div>
  <div class="row m-0 p-0" v-else>
    <small class="text-sm text-danger m-auto" style="font-size: xx-small">&nbsp;</small>
  </div>
</template>

<script>
export default {
  name: "ErrorMessage",
  props: {
    item: {
      type: Object,
      default: () => {
        return undefined
      }
    },
  }
};
</script>

<style scoped>

</style>