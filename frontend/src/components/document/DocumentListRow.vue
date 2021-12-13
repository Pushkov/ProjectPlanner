<template>
  <tr
      v-if="document !== undefined && document !== {}"
      @click="selectDocument(document.id)"
  >
    <td>
      {{ index }}
    </td>
    <td>
      {{ document.designation }}
    </td>
    <td>
      {{ document.name }}
    </td>

  </tr>
  <tr v-else>

  </tr>
</template>

<script>
import router from "@/router";

export default {
  name: "DocumentListRow",
  props: {
    document: {
      type: Object,
      default() {
        return {}
      }
    },
    index: {
      type: Number,
      default() {
        return 0;
      }
    }
  },
  methods: {
    selectDocument(id) {
      router.push('/planner/documents/' + id);
    }
  }
}
</script>

<style scoped>

</style>