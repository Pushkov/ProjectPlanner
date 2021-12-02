<template>
  <div class="row p-3">

    <table class="table table-striped">
      <thead class="thead-light">
      <tr>
        <td class="col-1">
          <div class="col-sm ">
            <b-button
                @click="showAll"
            ><h5 class="m-auto">{{ $t("message.overview.show-all") }}</h5></b-button>
          </div>
        </td>
        <td class="col-2">
          <div class="col-sm">
            <div class="row">
              <h5 class="mx-3"><h5 class="m-auto">{{ $t("message.overview.year") }}</h5></h5>
            </div>
            <b-form-select
                v-model="currentYear"
                :options="years"
                class="row text-secondary border rounded-lg"
                value-field="value"
                text-field="value"
                disabled-field="notEnabled"
                @change="searchItem"
            >
              <b-form-select-option :value="null">Все года</b-form-select-option>
            </b-form-select>
          </div>
        </td>
        <td class="col-2">
          <div class="col-sm">
            <div class="row">
              <h5 class="mx-3"><h5 class="m-auto">{{ $t("message.overview.month") }}</h5></h5>
            </div>
            <b-form-select
                v-model="currentMonth"
                :options="ALL_LOCALE_MONTHS"
                class="row text-secondary border rounded-lg"
                value-field="value"
                text-field="name"
                disabled-field="notEnabled"
                @change="searchItem"
            >
              <b-form-select-option :value="null">За весь год</b-form-select-option>

            </b-form-select>
          </div>
        </td>
        <td class="col-2">
          <h5 class="m-auto">{{ $t("message.overview.project-name") }}</h5></td>
        <td class="col-2">
          <h5 class="m-auto">{{ $t("message.overview.project-designation") }}</h5></td>
        <td class="col-2">
          <div class="col-sm">
            <div class="row">
              <h5 class="mx-3"><h5 class="m-auto">{{ $t("message.overview.department") }}</h5></h5>
            </div>
            <b-form-select
                v-model="currentDepartment"
                :options="departments"
                class="row text-secondary border rounded-lg"
                value-field="value"
                text-field="value"
                disabled-field="notEnabled"
                @change="searchItem"
            >
              <b-form-select-option :value="null">Вся контора</b-form-select-option>
            </b-form-select>
          </div>
        </td>
        <td class="col-2">
          <h5 class="m-auto">{{ $t("message.overview.basics") }}</h5></td>
        <td class="col-1">
          <div class="col-sm">
            <b-button
                @click="showAll"
            ><h5 class="m-auto">{{ $t("message.overview.show-current") }}</h5></b-button>
          </div>
        </td>
      </tr>
      </thead>
    </table>

  </div>
</template>

<script>
import {mapActions, mapGetters} from "vuex";

export default {
  name: "OverviewListSelectors",
  data() {
    return {
      currentYear: 2021,
      currentMonth: 12,
      currentDepartment: 'УПиР'
    }
  },
  props: {
    years: {
      type: Array,
      default:
          () => {
            return []
          }
    },
    months: {
      type: Array,
      default:
          () => {
            return []
          }
    },
    departments: {
      type: Array,
      default:
          () => {
            return []
          }
    },
  },
  computed: {
    ...mapGetters([
      'ALL_LOCALE_MONTHS',
    ]),
  },
  methods: {
    ...mapActions([
      'SEARCH_OVERVIEW_ITEM',
    ]),
    searchItem() {
      let item = {
        "year": this.currentYear,
        "month": this.currentMonth,
        "departmentName": this.currentDepartment,
      };
      this.SEARCH_OVERVIEW_ITEM(item);

    },
    showAll() {
      this.currentYear = null;
      this.currentMonth = null;
      this.currentDepartment = null;
      this.searchItem();
    }
  },
  mounted() {
    this.searchItem();
  }

}

</script>

<style scoped>

</style>