<template>
  <div class="basic-popup-body container-fluid">
    <template v-if="item !== undefined || item !== {}">
      <div class="row my-1 mx-2"
      >
        <div class="col-4 mr-2 ">key1</div>
        <input
            class="col-sm text-secondary  border rounded-lg"
            :readonly="!isEdit"
            :value="'value1'"
        />
        <face-icon class="col-1 mr-2"/>
        <!--          <div class="col-1 mr-2 bg-danger" v-else>del</div>-->
      </div>


      <!--      <template v-else>-->
      <!--        <div class="row my-1 mx-2">-->
      <!--          <div class="col-4 mr-2 ">{{ key }}</div>-->
      <!--          <input class="col-sm text-secondary  border rounded-lg"-->
      <!--                 :readonly="!isEdit"-->
      <!--                 :value="value"-->
      <!--          />-->
      <!--        </div>-->
      <!--      </template>-->
    </template>
    <template v-else>
      <h2>{{ errorMessage }}</h2>
    </template>
  </div>
</template>

<script>
import FaceIcon from 'vue-material-design-icons/FaceMan.vue';
import PlusIcon from 'vue-material-design-icons/Plus';

export default {
  name: "RoleModalView",
  comments: {
    PlusIcon,
    FaceIcon
  },
  data() {
    return {
      dataItem: {...this.item}
    }
  },
  props: {
    item: {
      type: Object,
      default: () => {
        return {}
      }
    },
    isEdit: {
      type: Boolean,
      default: false
    },
    errorMessage: {
      type: String,
      default: ''
    }
  },
  computed: {},
  methods: {
    returnItem() {
      this.$emit('returnItem', this.dataItem);
    }
  }

}
</script>

<style scoped>

</style>