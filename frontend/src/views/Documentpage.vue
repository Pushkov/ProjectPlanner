<template>
  <div>
    <NavigationBar/>
    <DocumentList/>
  </div>
</template>

<script>
import NavigationBar from "@/components/nav/NavigationBar";
import DocumentList from "@/components/document/DocumentList";

export default {
  name: "Index",
  components: {
    DocumentList,
    NavigationBar
  }
}
</script>

<style scoped>

</style>