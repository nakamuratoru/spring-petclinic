/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.vet;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ペットクリニックの獣医を表示するためのコントローラクラス
 */
@Controller
class VetController {

    private final VetRepository vetRepository;

    /**
     * 獣医コントロールを初期化するコンストラクタ
     *
     * @param clinicService VetRepositoryインスタンス
     */
    public VetController(VetRepository clinicService) {
        this.vetRepository = clinicService;
    }

    /**
     * 獣医リストを表示するメソッド
     *
     * @param page ページ番号
     * @param model モデルオブジェクト
     * @return 獣医リストのページテンプレート
     */
    @GetMapping("/vets.html")
    public String showVetList(@RequestParam(defaultValue = "1") int page, Model model) {
        Vets vets = new Vets();
        Page<Vet> paginated = findPaginated(page);
        vets.getVetList().addAll(paginated.toList());
        return addPaginationModel(page, paginated, model);
    }

    /**
     * ページネーションの情報をモデルに追加するメソッド
     *
     * @param page ページ番号
     * @param paginated ページオブジェクト
     * @param model モデルオブジェクト
     * @return 獣医リストのページテンプレート
     */
    private String addPaginationModel(int page, Page<Vet> paginated, Model model) {
        List<Vet> listVets = paginated.getContent();
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", paginated.getTotalPages());
        model.addAttribute("totalItems", paginated.getTotalElements());
        model.addAttribute("listVets", listVets);
        return "vets/vetList";
    }

    /**
     * 指定されたページでページ分割された獣医リストを取得するメソッド
     *
     * @param page ページ番号
     * @return ページ分割された獣医リスト
     */
    private Page<Vet> findPaginated(int page) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        return vetRepository.findAll(pageable);
    }

    /**
     * 獣医リストをJSON形式で表示するメソッド
     *
     * @return JSON形式の獣医リストオブジェクト
     */
    @GetMapping({ "/vets" })
    public @ResponseBody Vets showResourcesVetList() {
        Vets vets = new Vets();
        vets.getVetList().addAll(this.vetRepository.findAll());
        return vets;
    }

}

