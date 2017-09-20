package com.gxb.modules.constants;

/**
 * Created by ${sunninghai} on 15-12-14.
 */
public enum PublishStatus {
        NOT_PUBLISHED("10"),PRE_PUBLISHED("20"),PUBLISHED("30"),DISABLE("40");
        private String index;
        private PublishStatus(String index){
            this.index = index;
        }

        public String toString(){
            return index;
        }
}
