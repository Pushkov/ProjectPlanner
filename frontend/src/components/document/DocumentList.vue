<template>
  <div>
    <DocumentListRow v-for="doc of DOCUMENTS"
                     :key="doc.id"
                     :document="doc"
    />
  </div>
</template>

<script>
import {mapActions, mapGetters} from "vuex";
import DocumentListRow from "@/components/document/DocumentListRow";

export default {
  name: "DocumentList",
  components: {DocumentListRow},
  computed: {
    ...mapGetters([
      'DOCUMENTS'
    ]),
  },
  methods: {
    ...mapActions([
      'GET_ALL_DOCUMENTS'
    ]),
  },
  mounted() {
    this.GET_ALL_DOCUMENTS();
  }
}
</script>

<style scoped>

</style>