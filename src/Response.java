/**
 * Created by Chinmoy_M on 4/10/2017.
 */
public class Response {
    String comment;
    public Response(){
        this.comment="Helllllp";
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String s) {
        comment = s;
    }
    String[] products;
    /**
     * @return
     */
    public String[] getProducts() {
        if (products == null)
            products = new String[0];
        return products;
    }
    /**
     * @param strings
     */
    public void setProducts(String[] strings) {
        products = strings;
    }
    public String toString() {
        StringBuffer buf = new StringBuffer(2500);
        buf.append("Offered Products:").append("\n");
        for (int i = 0; i < getProducts().length; ++i) {
            buf.append("\t").append(getProducts()[i]).append("\n");
        }
        if (comment != null)
            buf.append("Comment: ").append(comment).append("\n");

        return buf.toString();
    }
}

