package br.com.alelo.consumer.consumerpat.model.dto.page;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 24/07/2021 | 08:41
 */

import br.com.alelo.consumer.consumerpat.util.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkDTO {

    private String prev;
    private String next;
    private String first;
    private String last;

    public LinkDTO(Integer page, Integer totalPage) {
        this.prev = page == 0 ? null : Constants.Links.APP_URL.concat("?page=").concat(String.valueOf(--page)).concat("&records=").concat("100");
        this.next = totalPage == page ? null : Constants.Links.APP_URL.concat("?page=").concat(String.valueOf(++page)).concat("&records=").concat("100");
        this.first = Constants.Links.APP_URL.concat("?page=").concat("0").concat("&records=").concat("100");
        this.last = Constants.Links.APP_URL.concat("?page=").concat(String.valueOf(totalPage)).concat("&records=").concat("100");
    }
}
