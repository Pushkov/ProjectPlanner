<template>
  <div class="basic-popup-body container-fluid">
    <div
        class="container-fluid m-1"
        v-for="(value,key) of dataItem"
        :key="key"
    >
      <template v-if="value.length === undefined && key.toLowerCase() !== 'id'">
        <div class="row my-1 mx-2 bg- border  rounded-lg ">
          <p class="m-auto"> {{ key }}
          </p>
        </div>
        <div class="row my-1 mx-2"
             v-for="(value1,key1) of value"
             :key="key1"
        >
          <div class="col-4 mr-2 ">{{ key1 }}</div>
          <input
                  class="col-sm text-secondary  border rounded-lg"
                  :readonly="!isEdit"
                  :value="value1"
          />
        </div>
      </template>

      <template v-else-if="Array.isArray(dataItem[key])">
        <div class="row my-1 mx-2">
          <div class="col-4 mr-2 ">{{ key }}</div>
          <div class="col-sm text-secondary  border rounded-lg">
            <ul class="m-auto">
              <template v-if="dataItem[key].length > 0">
                <li v-for="value of dataItem[key]"
                    :role="value"
                    :key="value.name"
                    style="list-style-type: none"
                >
                  {{ value.name }}
                </li>
              </template>
              <template v-else>

                <li style="list-style-type: none">
                  <pre><span> </span></pre>
                </li>
              </template>
            </ul>
          </div>
        </div>
      </template>

      <template v-else>
        <div class="row my-1 mx-2">
          <div class="col-4 mr-2 ">{{ key }}</div>
          <input class="col-sm text-secondary  border rounded-lg"
                 :readonly="!isEdit"
                 :value="value"
          />
        </div>
      </template>
    </div>
  </div>
</template>

<script>
export default {
  name: "BasicModalView",
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