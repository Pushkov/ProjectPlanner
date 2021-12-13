<template>
  <div slot="footer" class="row text-center">


        <span class="col-3">
                    <button
                        class="w-100 basic-btn-cansel text-black btn btn-outline-success rounded-lg"
                        @click="saveProject"
                    >
                    {{ $t('button.add') }}
                </button>
                </span>
    <span class="col-sm"></span>

    <span class="col-3">
                <button
                    class="w-100 basic-btn-cansel text-black btn btn-outline-secondary rounded-lg"
                    @click="cancel"
                >
                    {{ $t('button.cancel') }}
                </button>
            </span>
  </div>
</template>

<script>
export default {
  name: "DocumentProjectModalFooter",
  data() {
    return {
      isDelete: false
    }
  },
  props: {},
  methods: {
    saveProject() {
      this.$emit('saveProject');
    },
    cancel() {
      if (this.isCreate) {
        this.$emit('modalClose');
      } else {
        this.isDelete = false;
        this.$emit('editItem', false);
      }
    }
  }
}
</script>

<style scoped>

</style>