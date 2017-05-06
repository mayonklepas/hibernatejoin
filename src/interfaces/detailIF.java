/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import entities.Bukutelpon;
import entities.Detail;
import java.util.List;

/**
 *
 * @author Minami
 */
public interface detailIF {

    public void simpan(Detail d);

    public void update(Detail b);

    public void hapus(Detail b);

    public List<Bukutelpon> baca();

    public List<Bukutelpon> cari(String key);
}
