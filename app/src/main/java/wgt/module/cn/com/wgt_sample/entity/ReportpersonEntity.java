package wgt.module.cn.com.wgt_sample.entity;

import com.contrarywind.interfaces.IPickerViewData;


public class ReportpersonEntity implements IPickerViewData{

        private String key;
        private String value;

    public ReportpersonEntity(String key, String value) {
        this.key = key;
        this.value = value;
    }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "ReportpersonEntity{" +
                    "key='" + key + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }



        @Override
        public String getPickerViewText() {
            return value;
        }
    }

