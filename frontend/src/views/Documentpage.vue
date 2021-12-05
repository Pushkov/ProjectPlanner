<template>
  <div>
    <NavigationBar/>
    <template v-if="this.$route.fullPath === '/planner/documents'">

      <DocumentList/>
    </template>
    <template v-else>
      <Document/>
    </template>
  </div>
</template>

<script>
import NavigationBar from "@/components/nav/NavigationBar";
import DocumentList from "@/components/document/DocumentList";
import Document from "@/components/document/Document";

export default {
  name: "Index",
  components: {
    Document,
    DocumentList,
    NavigationBar
  }
}
</script>

<style scoped>

</style>