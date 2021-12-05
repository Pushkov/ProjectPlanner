<template>
  <div>
    {{ DOCUMENT }}
  </div>

</template>

<script>
import {mapActions, mapGetters} from "vuex";
import router from "@/router";

export default {
  name: "Document",
  data() {
    return {
      isEdit: false,
      id: 0,
      currentDocument: {...this.DOCUMENT}
    }
  },
  props: {
    document: {
      type: Object,
      default: () => {
        return {}
      }
    }
  },
  computed: {
    ...mapGetters([
      'DOCUMENT'
    ]),
    getItemId() {
      return this.$route.params.id;
    },

  },
  methods: {
    ...mapActions([
      'GET_DOCUMENT',
      'DELETE_DOCUMENT'
    ]),
    goBack() {
      router.go(-1)
    },
    editDocument() {
      // todo
    },
    deleteDocument() {
      this.DELETE_DOCUMENT(this.id).then(
          this.goBack()
      );
    }
  },
  mounted() {
    this.id = this.$route.params.id;
    this.GET_DOCUMENT(this.id);
  }
}
</script>

<style scoped>

</style>