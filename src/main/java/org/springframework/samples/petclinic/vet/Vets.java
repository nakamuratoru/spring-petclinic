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

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * ペットクリニックの獣医のリストを表すシンプルなドメインオブジェクトです。
 * 主に、 'vets' {@link org.springframework.web.servlet.view.xml.MarshallingView} で使用されます。
 *
 * @author Arjen Poutsma
 */
@XmlRootElement
public class Vets {

    private List<Vet> vets;

    /**
     * 獣医のリストを取得します。
     * 獣医が存在しない場合は新しいArrayListを作成します。
     *
     * @return 獣医のリスト
     */
    @XmlElement
    public List<Vet> getVetList() {
        if (vets == null) {
            vets = new ArrayList<>();
        }
        return vets;
    }

}
