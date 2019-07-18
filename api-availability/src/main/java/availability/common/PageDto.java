package availability.common;

/**
 * Created by dami on 09/07/19.
 */
public class PageDto {

    private int page = 0;
    private int size = 10;
    private String sortBy;

    public PageDto() {
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public String getSortBy() {
        return sortBy;
    }

    private PageDto(Builder builder) {
        page = builder.page;
        size = builder.size;
        sortBy = builder.sortBy;
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private int page = 0;
        private int size = 10;
        private String sortBy;

        private Builder() {
        }

        public Builder page(int val) {
            page = val;
            return this;
        }

        public Builder size(int val) {
            size = val;
            return this;
        }

        public Builder sortBy(String val) {
            sortBy = val;
            return this;
        }

        public PageDto build() {
            return new PageDto(this);
        }
    }
}
